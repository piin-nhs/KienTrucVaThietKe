const logger = require("../config/logger");
const notificationService = require("../services/notificationService");

const QUEUES = {
  PAYMENT_COMPLETED: "PAYMENT_COMPLETED",
  BOOKING_FAILED: "BOOKING_FAILED",
};

const initNotificationConsumer = async (channel) => {
  await channel.assertQueue(QUEUES.PAYMENT_COMPLETED, { durable: true });
  await channel.assertQueue(QUEUES.BOOKING_FAILED, { durable: true });

  channel.consume(QUEUES.PAYMENT_COMPLETED, (msg) => {
    if (msg !== null) {
      try {
        const data = JSON.parse(msg.content.toString());

        notificationService.handleSuccess(data);
      } catch (error) {
        logger.error(
          `[NOTIFICATION] Lỗi xử lý tin nhắn thành công: ${error.message}`,
        );
      }

      channel.ack(msg);
    }
  });

  channel.consume(QUEUES.BOOKING_FAILED, (msg) => {
    if (msg !== null) {
      try {
        const data = JSON.parse(msg.content.toString());

        notificationService.handleFailure(data);
      } catch (error) {
        logger.error(
          `[NOTIFICATION] Lỗi xử lý tin nhắn thất bại: ${error.message}`,
        );
      }

      channel.ack(msg);
    }
  });
};

module.exports = initNotificationConsumer;
