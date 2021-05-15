#include "../include/comparator.h"
ByWordCnt:: ByWordCnt(string word) : src_word(word) {}
bool ByWordCnt:: operator()(Chapter *A, Chapter *B) //If A<B
{
    int f1, f2;
    f1 = f2 = 0;
    //Counting words in both chapters
    for (Paragraph P : A->get_all_para())
    {
        for (Sentence S : P.get_all())
        {
            for (string word : S.get_all())
            {
                if (word.find(src_word) != string::npos)
                {
                    f1++;
                }
            }
        }
    }
    for (Paragraph P : B->get_all_para())
    {
        for (Sentence S : P.get_all())
        {
            for (string word : S.get_all())
            {
                if (word.find(src_word) != string::npos)
                {
                    f2++;
                }
            }
        }
    }
    //return if A<B is true
    //inverse sign used since we want to sort in descending order
    return f1 > f2;
}
bool ByWordCnt::operator()(Paragraph *A, Paragraph *B)
{
    int f1, f2;
    f1 = f2 = 0;
    //Counting words in both para
    for (Sentence S : A->get_all())
    {
        for (string word : S.get_all())
        {
            if (word.find(src_word) != string::npos)
            {
                f1++;
            }
        }
    }
    for (Sentence S : B->get_all())
    {
        for (string word : S.get_all())
        {
            if (word.find(src_word) != string::npos)
            {
                f2++;
            }
        }
    }
    //return if A<B is true
    //inverse sign used since we want to sort in descending order
    return f1 > f2;
}
