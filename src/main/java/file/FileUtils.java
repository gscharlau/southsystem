package file;

import parser.Parser;
import parser.Relatorio;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileUtils {

   private static final String DAT_EXTENSION = ".dat";
   private static final String DONE_DAT_EXTENSION = ".done.dat";
   private static final int FIRST_INDEX = 0;
   private static final String FILE_SEPARATOR = FileSystems.getDefault().getSeparator();

   public FileUtils(){
   }

   Relatorio extract(Path inputFile){
      Relatorio relatorio = null;
      try {
         if (null != inputFile  && inputFile.toString().endsWith(".dat")) {
            System.out.println("Extracting data from: " + inputFile);
            List<String> lines = Files.readAllLines(inputFile);
            Parser parser = new Parser();

            for (String line: lines) {
               parser.parse(line);
            }

            relatorio = parser.buildRelatorio();
         } else {
            System.err.format("Aborting extraction: '%s' is not a .DAT file\n",  inputFile);
         }
      } catch (IOException ioe) {
         ioe.printStackTrace();
      }
      return relatorio;
   }

   void compile(Path outputPath, Path inputFile, Relatorio relatorio){
      if(null != outputPath && null != inputFile && null != relatorio){
         System.out.println("Compiling relatorio: " + relatorio);
         File outputFile = createDoneFile(outputPath, inputFile);
         try(PrintWriter writer = new PrintWriter(outputFile)) {
            writer.println(relatorio.getMensagemTotalClientes());
            writer.println(relatorio.getMensagemTotalVendedores());
            writer.println(relatorio.getMensagemIdVendaMaisCara());
            writer.println(relatorio.getMensagemNomePiorVendedor());
         } catch (FileNotFoundException fnfe) {
            System.err.println(fnfe.getMessage());
         }
      }
   }

   void dispose(Path processedPath, Path inputFile){
      try {
         if(null != inputFile) {
            File oldInputFile = inputFile.toFile();
            File newFile = createFile(
                processedPath, inputFile.getFileName().toString());
            inputFile.toFile().renameTo(newFile);
            Files.deleteIfExists(oldInputFile.toPath());
            System.out.println("Disposing file: " + newFile);
         }
      } catch (IOException e) {
         e.printStackTrace();
      }
   }

   private File createDoneFile(Path outputPath, Path inputFile){
      String outputFileName = inputFile.getFileName().toString();
      int lastIndex = outputFileName.lastIndexOf(DAT_EXTENSION);

      StringBuilder fileName =
          new StringBuilder(outputFileName.substring(FIRST_INDEX, lastIndex))
          .append(DONE_DAT_EXTENSION);
      return createFile(outputPath, fileName.toString());
   }

   private File createFile(Path destinationPath, String fileName){
      StringBuilder fullFileName =
          new StringBuilder(destinationPath.toAbsolutePath().toString())
              .append(FILE_SEPARATOR)
              .append(fileName);
      System.out.println("New file created: " + fullFileName);
      return new File(fullFileName.toString());
   }
}
