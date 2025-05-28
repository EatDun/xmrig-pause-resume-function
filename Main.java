import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.InputStreamReader;


class Main {
    public static void main(String[] args) {
        boolean paused = false;

        //TOD boolean logic
        while(true) {
            int hour = LocalTime.now().getHour();
            boolean isPeak = hour >= 15 && hour < 19;
            if(isPeak && !paused) {
                stopStart("pause");
                System.out.println("Stopped mining");
                paused = true;
                try {
                    Thread.sleep(10800000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            else if(!isPeak && paused) {
                stopStart("resume");
                System.out.println("Resuming mining");
                paused = false;
            }
            getStatus();
            try {
                Thread.sleep(60000); // Check every minute
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //API call for pausing miner
    public static void stopStart(String method) {
        String command = "curl --silent --data '{\"method\":\"" + method + "\",>
                       + "-H \"Content-Type: application/json\" "
                       + "-H \"Authorization: Bearer 123\" "
                       + "http://127.0.0.1:16000/json_rpc";

        try {
            Process process = Runtime.getRuntime().exec(new String[] { "bash", >
            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //API call for checking status
    public static void getStatus() {
        String commandS = "curl -H \"Authorization: Bearer 123\" http://127.0.0>

        try {
            Process process = Runtime.getRuntime().exec(new String[] { "bash", >

            BufferedReader reader = new BufferedReader(new InputStreamReader(pr>
            String line;
            try {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            } catch (Exception e) {
                e.printStackTrace();
            }
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            process.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
