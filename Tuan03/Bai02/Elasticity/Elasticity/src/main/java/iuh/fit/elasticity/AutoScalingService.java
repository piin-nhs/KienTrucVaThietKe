package iuh.fit.elasticity;

import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class AutoScalingService {

    private ThreadPoolExecutor executor =
            new ThreadPoolExecutor(
                    2,
                    2,
                    60,
                    TimeUnit.SECONDS,
                    new LinkedBlockingQueue<>()
            );

    public String handleRequest() {
        autoScale();

        executor.submit(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        return "Active threads: " + executor.getPoolSize();
    }

    private void autoScale() {
        int queueSize = executor.getQueue().size();

        if (queueSize > 5) {
            executor.setMaximumPoolSize(10);
            executor.setCorePoolSize(10);
        } else {
            executor.setMaximumPoolSize(2);
            executor.setCorePoolSize(2);
        }
    }
}

