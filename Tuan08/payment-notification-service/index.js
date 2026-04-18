const express = require("express");
const connectDB = require("./config/database");
const connectRabbitMQ = require("./config/rabbitmq");
const logger = require("./config/logger");
const { initSocket } = require("./config/socket");
const initPaymentConsumer = require("./consumers/paymentConsumer");
const initNotificationConsumer = require("./consumers/notificationConsumer");


const app = express();
const http = require("http");
const server = http.createServer(app);
const PORT = 8084;

async function bootstrap() {
  try {
    await connectDB();

    const { channel } = await connectRabbitMQ();
    const io = initSocket(server);
    await initPaymentConsumer(channel);
    await initNotificationConsumer(channel);

    app.get("/", (req, res) =>
      res.json({ status: "UP", service: "Service 5 Layered" }),
    );

    server.listen(PORT, "0.0.0.0", () => {
      logger.info(`Server đang chạy tại http://localhost:${PORT}`);
    });
  } catch (error) {
    logger.error("Không thể khởi động hệ thống");
  }
}

bootstrap();
