package file;

import parser.Relatorio;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.*;

public class DiretorioListener {
   private WatchService watchService;
   private Ambiente environment;
   private FileUtils fileUtils;

   public DiretorioListener(Ambiente environment, FileUtils fileUtils) throws IOException{
      this.environment = environment;
      this.fileUtils = fileUtils;
      this.watchService = FileSystems.getDefault().newWatchService();
      this.environment.getInputPath().register(watchService, ENTRY_CREATE);
   }

   @SuppressWarnings("unchecked")
   public void listenToEvents() {
      for (;;) {
         WatchKey key = getWatchKey();

         for (WatchEvent<?> event: key.pollEvents()) {
            if (OVERFLOW == event.kind()) {
               continue;
            }
            digestFile(getInputFile((WatchEvent<Path>) event));
         }

         if (!key.reset()) {
            break;
         }
      }
   }

   private WatchKey getWatchKey(){
      WatchKey key = null;
      try {
         System.out.println("Aguardando Novos Arquivos...");
         key = watchService.take();
         Thread.sleep(1000);
      } catch (InterruptedException ie) {
         // ignored
      }
      return key;
   }

   private void digestFile(Path inputFile) {
      System.out.format("Processando: %s\n", inputFile);

      Relatorio relatorio = this.fileUtils.extract(inputFile);
      this.fileUtils.compile(
          this.environment.getOutputPath(), inputFile, relatorio);
      this.fileUtils.dispose(this.environment.getProcessedPath(), inputFile);
   }

   private Path getInputFile(WatchEvent<Path> event){
      Path inputFileName = event.context();
      return environment.getInputPath().resolve(inputFileName);
   }
}
