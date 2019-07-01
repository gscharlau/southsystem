package file;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Ambiente {

   private static final String HOMEPATH = "user.home";
   private static final String DATA_IN = "/data/in";
   private static final String DATA_OUT = "/data/out";
   private static final String DATA_PROCESSED = "/data/processed";
   
   private String base;
   private Path inputPath;
   private Path outputPath;
   private Path processedPath;
   
   public Ambiente(){
      this.init();
   }

   Path getInputPath() {
      return inputPath;
   }
   Path getOutputPath() {
      return outputPath;
   }
   Path getProcessedPath() {
      return processedPath;
   }

   private void init(){
      try {
         this.initInputPath();
         this.initOutputPath();
         this.initProcessedPath();
      } catch (Exception e) {
         exit(e.getMessage());
      }
   }
   
   private void initInputPath(){
      this.base = System.getProperty(HOMEPATH);
      if (null == this.base) {
         exit("Undefined environment variable 'user.home'");
      }
      String in = this.base + DATA_IN;
      this.inputPath = Paths.get(in);
   
      if (!this.inputPath.toFile().exists()) {
         createDirectory(in);
         //exit("Does not exist: " + in);
      } else if (!this.inputPath.toFile().isDirectory()) {
         exit("Not a directory: " + in);
      } else if (!this.inputPath.toFile().canRead()) {
         exit("Could not read: " + in);
      }
      System.out.println("Input Path: " + this.inputPath);
   }
   
   private void initOutputPath(){
      String out = this.base + DATA_OUT;
      this.outputPath = createDirectory(out);
      System.out.println("Relatorio Path: " + this.outputPath);
   }
   
   private void initProcessedPath(){
      String processed = this.base + DATA_PROCESSED;
      this.processedPath = createDirectory(processed);
      System.out.println("Processed Path: " + this.processedPath);
   }
   
   private Path createDirectory(String path) {
      Path directory = Paths.get(path);
      if (!directory.toFile().exists()) {
         if (!directory.toFile().mkdirs()) {
            throw new RuntimeException("Could not create: " + path);
         }
      } else if (!directory.toFile().isDirectory()) {
         throw new RuntimeException("Not a directory: " + path);
      }
      return directory;
   }

   private void exit(String message) {
      System.err.println(message);
      System.exit(1);
   }
}
