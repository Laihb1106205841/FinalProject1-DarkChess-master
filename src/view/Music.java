package view;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.applet.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URI;
import java.nio.file.*;//
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


//    import java.io.BufferedInputStream;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import javazoom.jl.decoder.JavaLayerException;
//import javazoom.jl.player.*;
//
//    class Play0 extends Thread{
//        Player player;
//        String music;
//        public Play0(String file) {
//            this.music = file;
//        }
//        public void run() {
//            try {
//                play();
//            } catch (FileNotFoundException | JavaLayerException e) {
//                e.printStackTrace();
//            }
//        }
//        public void play() throws FileNotFoundException, JavaLayerException {
//            BufferedInputStream buffer = new BufferedInputStream(new FileInputStream(music));
//            player = new Player(buffer);
//            player.play();
//        }
//    }
class Music {
    static Clip clip;

    public static void playMusic() {
        try {
            //这里面放 绝对路径，音频必须是wav格式，用音频转换软件 把mp3 转成wav格式
            File musicPath = new File("C:\\Users\\11062\\Desktop\\课本\\计算机\\DarkChess-master\\src\\ADDbyGB\\国产特摄剧《战狠铠甲》合体集锦   豆瓣评分9.9  （任何邪恶终将绳之以法） - 1.国产特摄剧《战狠铠甲》合体集锦   豆瓣评分9.9(Av514443234,P1)_1.WAV");

            File musicPath2 =new File("C:\\Users\\11062\\Desktop\\课本\\计算机\\DarkChess-master\\src\\ADDbyGB\\伟大的卫国战争-One-Minute-Of-Proud.wav");

            if (musicPath2.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath2);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(0.0f);//设置音量，范围为 -60.0f 到 6.0f
                clip.start();
            //    clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {

            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public static void stopMusic(){
        if (clip != null) {
            clip.close();
        }
    }
    public static void playMusic2() {
        try {
            //这里面放 绝对路径，音频必须是wav格式，用音频转换软件 把mp3 转成wav格式
            File musicPath = new File("C:\\Users\\11062\\Desktop\\课本\\计算机\\DarkChess-master\\src\\ADDbyGB\\伟大的卫国战争-One-Minute-Of-Proud.wav");

            File musicPath2 =new File("C:\\Users\\11062\\Desktop\\课本\\计算机\\DarkChess-master\\src\\ADDbyGB\\伟大的卫国战争-The-teletype.wav");

            if (musicPath2.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath2);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(0.0f);//设置音量，范围为 -60.0f 到 6.0f
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);
//                if(clip.isRunning()){
//                               playMusic3();
////                    }
//                }

                //    clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else if(musicPath.exists()){ AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath2);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(0.0f);//设置音量，范围为 -60.0f 到 6.0f
                clip.start();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);

            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }






    public static void playMusic3() {
        try {
            //这里面放 绝对路径，音频必须是wav格式，用音频转换软件 把mp3 转成wav格式


            File musicPath = new File("C:\\Users\\11062\\Desktop\\课本\\计算机\\DarkChess-master\\src\\ADDbyGB\\国产特摄剧《战狠铠甲》合体集锦   豆瓣评分9.9  （任何邪恶终将绳之以法） - 1.国产特摄剧《战狠铠甲》合体集锦   豆瓣评分9.9(Av514443234,P1)_1.WAV");

            File musicPath2 =new File("C:\\Users\\11062\\Desktop\\课本\\计算机\\DarkChess-master\\src\\ADDbyGB\\伟大的卫国战争-One-Minute-Of-Proud.wav");

            if (musicPath2.exists()) {
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath2);
                clip = AudioSystem.getClip();
                clip.open(audioInput);
                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(0.0f);//设置音量，范围为 -60.0f 到 6.0f
                clip.start();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {

            }


        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    /**
     * @description: MP4文件在线播放
     * @author: Re、ZOO2
     * @date: 2021/7/25 22:55
     * @param: [request, response, floderPath文件夹路径, fileName文件名称]
     * @return: com.lvmvp.configconsts.constant.ResultView
     **/
//    @GetMapping(value = "/playMp4/{fileName}",produces ="application/json;charset=utf-8")
//    public ResultView playMp4(HttpServletRequest request, HttpServletResponse response,
//                              @PathVariable("fileName") String fileName){
//        String floderPath = "D:/Desktop/";
//        FileNormalOperationUtils.aloneVideoPlay(request,response,floderPath,fileName);
//        return null;
//    }
    /**
     * @description: 在线播放MP4文件
     * @author: Re、ZOO2
     * @date: 2021/7/25 22:50
     * @param: [request, floderPath 文件夹路径, fileName 文件名称, response]
     * @return: void
     **/


    public class ExecutePy
    {
        public static void main(String[] args) {
            try {
                String exe = "python";
                String command = "/Users/jinchengll/Documents/temp/test.py";
                String[] cmdArr = new String[] { exe, command };
                Process process = Runtime.getRuntime().exec(cmdArr);
                BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String line;
                while( ( line = in.readLine() ) != null ) {
                    System.out.println(line);
                }
                in.close();
                int result = process.waitFor();
                System.out.println("执行结果:" + result);
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }






}



