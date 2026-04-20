const Payment = require("../models/paymentModel");
const logger = require("../config/logger");

class PaymentService {
  async processPayment(payload) {
    const bookingData = payload.data || payload;

    const isSuccess = Math.random() > 0.3;
    const paymentStatus = isSuccess ? "SUCCESS" : "FAILED";
    const transId = isSuccess ? `TXN-${Date.now()}` : null;
    const resultMessage = isSuccess ? "Thanh toán thành công" : "Lỗi giao dịch";

    try {
      const newPayment = new Payment({
        bookingId: bookingData.bookingId,
        userId: bookingData.userId,
        status: paymentStatus,
        transactionId: transId,
        message: resultMessage,
      });
      await newPayment.save();
      logger.info(`[DATABASE] Lưu lịch sử giao dịch #${bookingData.bookingId}`);
    } catch (error) {
      logger.error(`[DATABASE] Lỗi lưu: ${error.message}`);
    }

    return {
      ...bookingData,
      status: paymentStatus,
      transactionId: transId,
      message: resultMessage,
      completedAt: new Date().toISOString(),
    };
  }
}

module.exports = new PaymentService();
