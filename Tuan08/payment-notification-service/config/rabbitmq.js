const amqp = require("amqplib");
const logger = require("./logger");

const AMQP_URL =
  "amqps://mwdpomue:DI1u8Xz2UHOWm1yqlxmZCz7H40MDCe06@kingfisher.lmq.cloudamqp.com/mwdpomue";

const connectRabbitMQ = async () => {
  try {
    const connection = await amqp.connect(AMQP_URL);
    const channel = await connection.createChannel();
    logger.info("RabbitMQ Cloud đã kết nối thành công");
    return { connection, channel };
  } catch (error) {
    logger.error(`Lỗi kết nối RabbitMQ: ${error.message}`);
    throw error;
  }
};

module.exports = connectRabbitMQ;
