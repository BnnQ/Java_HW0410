package me.bnnq.models;

import java.util.Objects;

public class Word implements Comparable<Word>
{
    private String word;
    private String translation;
    private int numberOfRequests;

    public Word(String word, String translation)
    {
        this.word = word;
        this.translation = translation;
        this.numberOfRequests = 0;
    }

    public String getWord()
    {
        return word;
    }

    public String getTranslation()
    {
        return translation;
    }

    public int getNumberOfRequests()
    {
        return numberOfRequests;
    }

    public void setWord(String word)
    {
        this.word = word;
    }

    public void setTranslation(String translation)
    {
        this.translation = translation;
    }

    public void setNumberOfRequests(int numberOfRequests)
    {
        this.numberOfRequests = numberOfRequests;
    }

    public void incrementNumberOfRequests()
    {
        this.numberOfRequests++;
    }

    @Override
    public int compareTo(Word o)
    {
        return numberOfRequests - o.getNumberOfRequests();
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Word word = (Word) obj;
        return Objects.equals(this.word, word.word);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(word);
    }
}
