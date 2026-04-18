const mongoose = require("mongoose");
const logger = require("./logger");

const MONGO_URI =
  "mongodb+srv://nguyenhung2004200_db_user:W87UoUWquGGfbjXv@cluster1.gypblkl.mongodb.net/movie_ticket_db?retryWrites=true&w=majority";

const connectDB = async () => {
  try {
    await mongoose.connect(MONGO_URI);
    logger.info("MongoDB Atlas đã kết nối thành công");
  } catch (error) {
    logger.error(`Lỗi kết nối MongoDB: ${error.message}`);
    process.exit(1);
  }
};

module.exports = connectDB;
