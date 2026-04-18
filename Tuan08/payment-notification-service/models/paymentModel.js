const mongoose = require("mongoose");

const paymentSchema = new mongoose.Schema({
  bookingId: { type: String, required: true },
  userId: { type: String, required: true },
  amount: { type: Number, default: 50000 },
  status: { type: String, enum: ["SUCCESS", "FAILED"], required: true },
  transactionId: { type: String },
  message: { type: String },
  createdAt: { type: Date, default: Date.now },
});

module.exports = mongoose.model("Payment", paymentSchema);
