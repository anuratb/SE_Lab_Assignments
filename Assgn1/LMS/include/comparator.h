#include <iostream>
#include <vector>
#include <algorithm>
#include "novel.h"
#include "chapter.h"
#include "paragraph.h"
#include "sentence.h"
using namespace std;
#ifndef COMP_INC
#define COMP_INC
//============================================================================
class ByWordCnt //comparator class for finding paragraph/chapter with most src_words
{
    string src_word; //Search word

public:
    ByWordCnt(string word);                      //CR
    //Descending Order
    bool operator()(Chapter *A, Chapter *B);     //f Chap A before Chap B in sorted list int terms of no. of search matches
    bool operator()(Paragraph *A, Paragraph *B); //if Para A before Para B in sorted list int terms of no. of search matches
};
//========================================================================
#endif