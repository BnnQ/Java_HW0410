package me.bnnq;

import me.bnnq.models.Word;
import me.bnnq.utils.ScannerUtilities;

import java.util.Comparator;
import java.util.HashSet;

public class LanguageDictionary
{
    private final String sourceLanguage;
    private final String destinationLanguage;
    private final HashSet<Word> words;

    public LanguageDictionary(String sourceLanguage, String destinationLanguage)
    {
        this.sourceLanguage = sourceLanguage;
        this.destinationLanguage = destinationLanguage;
        words = new HashSet<>();
    }

    public String getSourceLanguage()
    {
        return sourceLanguage;
    }

    public String getDestinationLanguage()
    {
        return destinationLanguage;
    }

    public void inputWords()
    {
        int numberOfWords = ScannerUtilities.scanInt("Enter number of words: ");
        for (int i = 0; i < numberOfWords; i++)
        {
            String word = ScannerUtilities.scanString("Enter word: ");
            String translation = ScannerUtilities.scanString("Enter translation: ");
            words.add(new Word(word, translation));
        }
    }

    public String getTranslationOrNull(String word)
    {
        if (!words.contains(new Word(word, null)))
            return null;

        @SuppressWarnings("OptionalGetWithoutIsPresent") Word wordEntry = words.stream().filter(w -> w.getWord().equals(word)).findFirst().get();

        words.remove(wordEntry);
        wordEntry.incrementNumberOfRequests();
        words.add(wordEntry);
        return wordEntry.getTranslation();
    }

    public void addWord(Word word)
    {
        words.add(word);
    }

    public boolean tryReplaceWord(String word, String newWord)
    {
        Word wordEntry = words.stream().filter(w -> w.getWord().equals(word)).findFirst().orElse(null);

        if (wordEntry != null)
        {
            wordEntry.setWord(newWord);
            return true;
        }

        return false;
    }

    public void removeWord(String word)
    {
        Word wordEntry = words.stream().filter(w -> w.getWord().equals(word)).findFirst().orElse(null);
        if (wordEntry != null)
            words.remove(wordEntry);
    }

    public void addTranslation(String word, String translation)
    {
        Word wordEntry = words.stream().filter(w -> w.getWord().equals(word)).findFirst().orElse(null);

        if (wordEntry != null)
        {
            wordEntry.setTranslation(translation);
        }
        else
        {
            words.add(new Word(word, translation));
        }
    }

    public boolean tryReplaceTranslation(String word, String newTranslation)
    {
        Word wordEntry = words.stream().filter(w -> w.getWord().equals(word)).findFirst().orElse(null);

        if (wordEntry != null)
        {
            wordEntry.setTranslation(newTranslation);
            return true;
        }

        return false;
    }

    public void removeTranslation(String word)
    {
        words.stream().filter(w -> w.getWord().equals(word)).findFirst().ifPresent(words::remove);
    }

    public Word[] getTop10PopularWords()
    {
        return words.stream()
                .sorted(Comparator.reverseOrder()) // sort in descending order
                .limit(10)
                .toArray(Word[]::new);
    }

    public Word[] getTop10UnpopularWords()
    {
        return words.stream()
                .sorted()
                .limit(10)
                .toArray(Word[]::new);
    }

}
