const logger = require("../config/logger");
const { getIO } = require("../config/socket"); 

class NotificationService {
  handleSuccess(data) {
    logger.info(
      `[NOTIFICATION] User ${data.userId} đã đặt đơn #${data.bookingId} thành công! (Mã GD: ${data.transactionId})`,
    );

    const io = getIO();

    if (io) {
      io.emit("PAYMENT_RESULT", {
        bookingId: data.bookingId,
        status: "SUCCESS",
        message: `Booking #${data.bookingId} thành công!`,
        transactionId: data.transactionId,
      });
    }
  }

  handleFailure(data) {
    logger.error(
      `[NOTIFICATION] Rất tiếc, thanh toán đơn #${data.bookingId} của User ${data.userId} thất bại. Lỗi: ${data.message}`,
    );

    const io = getIO();

    if (io) {
      io.emit("PAYMENT_RESULT", {
        bookingId: data.bookingId,
        status: "FAILED",
        message: data.message,
      });
    }
  }
}

module.exports = new NotificationService();
