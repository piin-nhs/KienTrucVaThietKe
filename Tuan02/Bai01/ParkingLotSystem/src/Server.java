import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class Server {

    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(PORT), 0);


        server.createContext("/enter", exchange -> {
            if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendResponse(exchange, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            System.out.println("--> [REQUEST] Có xe xin vào bãi...");

            Vehicle v = VehicleFactory.createRandomVehicle();

            int slot = ParkingLot.getInstance().park(v);

            String response;
            if (slot == -1) {
                response = "{\"status\":\"full\"}";
                System.out.println("    [!] Bãi đầy! Từ chối xe: " + v.getPlate());
            } else {
                response = String.format(
                        "{\"status\":\"ok\", \"slot\":%d, \"vehicle\":%s}",
                        slot,
                        v.toJson()
                );
                System.out.println("    [OK] " + v.getType() + " (" + v.getPlate() + ") -> Ô số " + (slot + 1));
            }

            sendResponse(exchange, 200, response);
        });

        server.createContext("/exit", exchange -> {
            if (!"GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                sendResponse(exchange, 405, "{\"error\":\"Method not allowed\"}");
                return;
            }

            try {
                Map<String, String> params = queryToMap(exchange.getRequestURI().getQuery());

                if (params.containsKey("slot")) {
                    int slot = Integer.parseInt(params.get("slot"));

                    ParkingLot.getInstance().leave(slot);

                    System.out.println("<-- [EXIT] Xe tại ô " + (slot + 1) + " đã rời bãi.");
                    sendResponse(exchange, 200, "{\"status\":\"leaved\"}");
                } else {
                    sendResponse(exchange, 400, "{\"status\":\"error\", \"message\":\"Thieu tham so slot\"}");
                }
            } catch (NumberFormatException e) {
                sendResponse(exchange, 400, "{\"status\":\"error\", \"message\":\"Slot phai la so nguyen\"}");
            } catch (Exception e) {
                e.printStackTrace();
                sendResponse(exchange, 500, "{\"status\":\"error\", \"message\":\"Loi server\"}");
            }
        });

        System.out.println("==================================================");
        System.out.println("SERVER PIXEL PARKING ĐANG CHẠY TẠI PORT " + PORT);
        System.out.println("==================================================");

        server.start();
    }

    private static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type,Authorization");
        exchange.getResponseHeaders().add("Content-Type", "application/json; charset=UTF-8");

        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);

        exchange.sendResponseHeaders(statusCode, bytes.length);

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }

    private static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        if (query == null) return result;

        for (String param : query.split("&")) {
            String[] entry = param.split("=");
            if (entry.length > 1) {
                result.put(entry[0], entry[1]);
            } else {
                result.put(entry[0], "");
            }
        }
        return result;
    }
}