// Nitin Dev
// NDEV
// 112298641

import java.io.*;
import java.util.*;
import java.nio.file.*;
import java.util.concurrent.*;
import java.util.Map.*;

public class WordCounter {

    // The following are the ONLY variables we will modify for grading.
    // The rest of your code must run with no changes.
    public static final Path FOLDER_OF_TEXT_FILES = Paths.get("src/txtFolder");              // path to the folder where input text files are located
    public static final Path WORD_COUNT_TABLE_FILE = Paths.get("src/wordCountTXT.txt");     // path to the output plain-text (.txt) file
    public static final int NUMBER_OF_THREADS = 1;                                              // max. number of threads to spawn

    public static void main(String... args) {
        try {
            PrintStream output = new PrintStream(new FileOutputStream(WORD_COUNT_TABLE_FILE.toString()));
            System.setOut(output);
        } catch (Exception e) {
            e.printStackTrace();
        }

        long start = System.currentTimeMillis();

        List<String> allFILES = fileFromPath();                      // list of files from folder
        Map<String, List<Integer>> onlyWORDS = new ConcurrentHashMap<>();        // save all the words and # of times they repeat

        dealingWithThreads(onlyWORDS, allFILES, NUMBER_OF_THREADS);

        TreeMap<String, List<Integer>> sortedOUT = new TreeMap<>(onlyWORDS);
        Set<Entry<String, List<Integer>>> sortedCONTENT = sortedOUT.entrySet();

        long end = System.currentTimeMillis();

        System.out.print(createTable.buildTable(sortedCONTENT, allFILES));

        long duration = end - start;
        //System.out.println();
        //System.out.println("Time: " + duration + " ms");

    }

    private static void dealingWithThreads(Map<String, List<Integer>> onlyWORDS, List<String> allFILES, int numberOfThreads) {
        int fileIndex = 0;

        if (numberOfThreads <= 1) {     // main thread
            for (String file : allFILES) {
                readingFile(onlyWORDS, file, allFILES.size(), fileIndex);
                fileIndex++;
            }
        } else {
            if (numberOfThreads >= allFILES.size()) {
                numberOfThreads = allFILES.size() - 1;
            }

            List<readThread> threads = new LinkedList<>();
            int threadIndex = 0;

            for (int usedThreads = 1; usedThreads < numberOfThreads; usedThreads++) {
                for (String file : allFILES) {
                    threads.add(new readThread(onlyWORDS, file, allFILES.size(), fileIndex));
                    threads.get(threadIndex).start();
                    threadIndex++;
                    fileIndex++;
                }
                for (Thread t : threads) {
                    try {
                        t.join();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    public static void readingFile(Map<String, List<Integer>> hashMap, String pathofFile, int fileCount, int currentFileIndex) {
        try {
            FileReader f = new FileReader(pathofFile);
            BufferedReader txtFile = new BufferedReader(f);

            String fileLine = "";
            String[] allWordsInLine;
            List<Integer> wordCount;
            List<Integer> temp;

            while ((fileLine = txtFile.readLine()) != null) {
                allWordsInLine = fileLine.split(" ");
                onlyLetters(allWordsInLine);

                for (String word : allWordsInLine) {
                    if (word.length() > createTable.longestWord) {    // longest character
                        createTable.setLongestWordLength(word.length());
                    }

                    if (hashMap.containsKey(word)) {
                        temp = hashMap.get(word);
                        temp.set(currentFileIndex, temp.get(currentFileIndex) + 1);
                        hashMap.put(word, temp);
                    } else {
                        wordCount = new ArrayList<>();
                        wordCount = zeroLst(fileCount);
                        wordCount.set(currentFileIndex, 1);
                        hashMap.put(word, wordCount);
                    }
                }
            }
        } catch (Exception e) {
            return;
        }
    }


    public static void onlyLetters(String[] words) {
        int x = 0;
        for (String w : words) {
            words[x] = w.replaceAll("[^a-zA-Z0-9\\s]", "").toLowerCase();
            x++;
        }
    }


    public static ArrayList<Integer> zeroLst(int numFiles) {
        ArrayList<Integer> zL = new ArrayList<>();
        for (int x = 0; x < numFiles; x++) {
            zL.add(0);
        }
        return zL;
    }

    public static List<String> fileFromPath() {
        String name = "";
        List<String> path = new ArrayList<>();
        File txtFolder = new File(FOLDER_OF_TEXT_FILES.toString());
        File[] filesInFolder = txtFolder.listFiles();

        for (File txtFile : filesInFolder) {
            path.add(txtFile.getPath());
            name = txtFile.getName();
            name = name.substring(0, name.lastIndexOf('.'));

            if (name.length() > createTable.longestFileName) {
                createTable.setLongestFileNameLength(name.length());
            }
        }
        Collections.sort(path);
        return path;
    }
}



class readThread extends Thread {
    private int numberOfFiles;
    private int currentFILE;
    private String filePATH;
    private Map hMap;

    public readThread(Map hMap, String filePATH, int numberOfFiles, int currentFILE) {
        this.hMap = hMap;
        this.filePATH = filePATH;
        this.numberOfFiles = numberOfFiles;
        this.currentFILE = currentFILE;
    }

    public void run() {
        WordCounter.readingFile(hMap, filePATH, numberOfFiles, currentFILE);
    }
}



class createTable {
    public static int longestWord = 0;
    public static int longestFileName = 0;

    public static void setLongestWordLength(int wordLen) {
        longestWord = wordLen;
    }

    public static void setLongestFileNameLength(int fileLen) {
        longestFileName = fileLen;
    }


    public static String buildTable(Set<Entry<String, List<Integer>>> filteredContent, List<String> lstFILES) {
        StringBuilder out = new StringBuilder();
        addTitles(lstFILES, out);
        addToValuesToTable(filteredContent, out);
        return out.toString();
    }

    private static void addTitles(List<String> lstFILES, StringBuilder out) {
        File f;
        String fN;

        out.append(String.format("%-" + (longestWord + 1) + "s", " "));

        for (String p : lstFILES) {
            f = new File(p);
            fN = f.getName();
            fN = fN.substring(0, fN.lastIndexOf('.'));
            out.append(String.format("%-" + (longestFileName + 4) + "s", fN));
        }
        out.append("total");
        out.append("\n");
    }

    private static void addToValuesToTable(Set<Entry<String, List<Integer>>> filteredContent, StringBuilder out) {
        List<Integer> currentElement;
        int sum = 0;

        for (Entry<String, List<Integer>> ELEMENT : filteredContent) {
            currentElement = ELEMENT.getValue();

            sum = currentElement.stream().mapToInt(Integer::intValue).sum();
            out.append(String.format("%-" + (longestWord + 1) + "s", ELEMENT.getKey()));

            for (Integer wordCount : currentElement) {
                out.append(String.format("%-" + (longestFileName + 4) + "d", wordCount));
            }

            out.append(String.format("%-5d", sum));
            out.append("\n");
        }
    }
}