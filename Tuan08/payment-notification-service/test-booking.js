const amqp = require("amqplib");
const AMQP_URL =
  "amqps://mwdpomue:DI1u8Xz2UHOWm1yqlxmZCz7H40MDCe06@kingfisher.lmq.cloudamqp.com/mwdpomue";

async function mockBookingService() {
  const connection = await amqp.connect(AMQP_URL);
  const channel = await connection.createChannel();
  await channel.assertQueue("BOOKING_CREATED", { durable: true });

  const mockData = {
    bookingId: Math.floor(Math.random() * 1000),
    userId: "A",
    movieId: "MOV_001",
    seats: ["A1", "A2"],
  };

  channel.sendToQueue("BOOKING_CREATED", Buffer.from(JSON.stringify(mockData)));
  console.log(`Đã gửi giả lập Booking #${mockData.bookingId} lên RabbitMQ`);

  setTimeout(() => {
    connection.close();
    process.exit(0);
  }, 500);
}

mockBookingService();
