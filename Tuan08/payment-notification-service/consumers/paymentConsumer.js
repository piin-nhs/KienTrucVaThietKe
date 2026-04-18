const paymentService = require("../services/paymentService");
const logger = require("../config/logger");

const QUEUES = {
  BOOKING_CREATED: "BOOKING_CREATED",
  PAYMENT_COMPLETED: "PAYMENT_COMPLETED",
  BOOKING_FAILED: "BOOKING_FAILED",
};

const initPaymentConsumer = async (channel) => {
  await channel.assertQueue(QUEUES.PAYMENT_COMPLETED, { durable: true });
  await channel.assertQueue(QUEUES.BOOKING_FAILED, { durable: true });
  await channel.assertQueue(QUEUES.BOOKING_CREATED, { durable: true });

  channel.consume(QUEUES.BOOKING_CREATED, async (msg) => {
    if (msg !== null) {
      try {
        const bookingData = JSON.parse(msg.content.toString());
        logger.info(`[CONSUMER] Nhận đơn #${bookingData.bookingId}`);

        await new Promise((resolve) => setTimeout(resolve, 2000));

        const result = await paymentService.processPayment(bookingData);

        const targetQueue =
          result.status === "SUCCESS"
            ? QUEUES.PAYMENT_COMPLETED
            : QUEUES.BOOKING_FAILED;

        channel.sendToQueue(targetQueue, Buffer.from(JSON.stringify(result)));

        channel.ack(msg);
      } catch (error) {
        logger.error(`[CONSUMER] Lỗi xử lý: ${error.message}`);
        channel.ack(msg);
      }
    }
  });
};

module.exports = initPaymentConsumer;
