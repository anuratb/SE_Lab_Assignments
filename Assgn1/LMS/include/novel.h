
#ifndef NOVEL_INCLUDE
#define NOVEL_INCLUDE
//==========================================================================
#include <iostream>
#include <vector>
#include "book.h"
#include "chapter.h"
using namespace std;
class Novel : public Book
{
    vector<Chapter> chapters;//List of Chapters

public:
    Novel();                                                                                                          //CR
    Novel(string _title, string _author, string _release_date, string lang, string file_name);                        //CR
    Novel(string _title, string _author, string _release_date, string lang, string file_name, vector<Chapter> chaps); //CR
    Novel(const Novel &other);                                                                                        //CCR

    Novel &operator=(const Novel &other); //CAO
    string get_type();                    //to get type
    int len();                            //for getting no. of chapters
    void add_chap(Chapter cp);            //for adding a chapter
    Chapter get(int ind);                 //for getting chapter at index ind
    vector<Chapter> get_all_chap();       //for getting all chapters in a vector
};
//==========================================================================
#endif