const paymentService = require("../services/paymentService");
const logger = require("../config/logger");

const EXCHANGE_NAME = "booking_exchange"; 
const ROUTING_KEY = "BOOKING_CREATED"; 

const QUEUES = {
  BOOKING_CREATED: "BOOKING_CREATED",
  PAYMENT_COMPLETED: "PAYMENT_COMPLETED",
  BOOKING_FAILED: "BOOKING_FAILED",
};

const initPaymentConsumer = async (channel) => {
  await channel.assertQueue(QUEUES.PAYMENT_COMPLETED, { durable: true });
  await channel.assertQueue(QUEUES.BOOKING_FAILED, { durable: true });
  await channel.assertQueue(QUEUES.BOOKING_CREATED, { durable: true });

  await channel.bindQueue(QUEUES.BOOKING_CREATED, EXCHANGE_NAME, ROUTING_KEY);

  channel.consume(QUEUES.BOOKING_CREATED, async (msg) => {
    if (msg !== null) {
      try {
        const payload = JSON.parse(msg.content.toString());

        const bookingData = payload.data || payload;

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
