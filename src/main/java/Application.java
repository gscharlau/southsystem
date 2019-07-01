import file.Ambiente;
import file.FileUtils;
import file.DiretorioListener;

import java.io.IOException;

public class Application {

   public static void main(String[] args) throws IOException {
      DiretorioListener diretorioListener =
         new DiretorioListener(new Ambiente(), new FileUtils());
      diretorioListener.listenToEvents();
   }
}
