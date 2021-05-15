/**
 * Name : Anurat Bhattacharya
 * Roll : 19CS10071
 */
#ifndef BOOK_INCLUDE
#define BOOK_INCLUDE
//======================================================================
#include <iostream>
#include <vector>

using namespace std;
class Book
{
    /**
     * title: Title of Book 
     * author: Author of Book
     * release_date: Release date of book 
     * lang:Language of Book
     */
protected:
    string title;
    string author;
    string release_date;
    string lang;
    string fl_nme;

public:
    Book();                                                                                   //CR
    Book(string _title, string _author, string _release_date, string lang, string file_name); //CR
    Book(const Book &other);                                                                  //CCR
    Book &operator=(const Book &other);                                                       //CAO
    virtual string get_type();                                                                //to get type
    string get_title() const;                                                                 //for getting title
    string get_auth() const;                                                                  //for getting suthor
    string get_date() const;                                                                  //for getting release date
    string get_lang() const;                                                                  //for getting language of the book
    string get_fl_nme() const;
    void set_title(string str); //for setting title
    void set_auth(string str);  //for setting suthor
    void set_date(string str);  //for setting release date
    void set_lang(string str);  //for setting language of the book
};
//==========================================================================
#endif
