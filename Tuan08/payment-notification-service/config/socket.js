const { Server } = require("socket.io");
const logger = require("./logger");

let io;

const initSocket = (server) => {
  io = new Server(server, {
    cors: {
      origin: "*", 
      methods: ["GET", "POST"],
    },
  });

  io.on("connection", (socket) => {
    logger.info(`[SOCKET] Client connected: ${socket.id}`);

    socket.on("disconnect", () => {
      logger.info(`[SOCKET] Client disconnected: ${socket.id}`);
    });
  });

  return io;
};

const getIO = () => {
  if (!io) {
    throw new Error("Socket chưa được khởi tạo!");
  }
  return io;
};

module.exports = {
  initSocket,
  getIO,
};
