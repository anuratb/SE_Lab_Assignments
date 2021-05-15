#include "../include/book.h"
Book::Book(){};                                                                                                                                                //CR
Book::Book(string _title, string _author, string _release_date, string lang, string file_name) : title(_title), author(_author), lang(lang), fl_nme(file_name) //CR
{
    release_date = _release_date;
}
Book::Book(const Book &other) //CCR
{
    this->author = other.author;
    this->lang = other.lang;
    this->release_date = other.release_date;
    this->title = other.title;
    this->fl_nme = other.fl_nme;
}
Book &Book::operator=(const Book &other) //CAO
{
    this->author = other.author;
    this->lang = other.lang;
    this->release_date = other.release_date;
    this->title = other.title;
    this->fl_nme = other.fl_nme;
    return *this;
}
string Book::get_type() //returns type
{
    string str = "Book";
    return str;
}
string Book::get_title() const { return title; }   //for getting title
string Book::get_auth() const { return author; }   //for getting suthor
string Book::get_date() const { return release_date; } //for getting release date
string Book::get_lang() const { return lang; }     //for getting language of the book
string Book::get_fl_nme() const { return fl_nme; }
void Book::set_title(string str) { this->title = str; }                         //for setting title
void Book::set_auth(string str) { this->author = str; }                         //for setting suthor
void Book::set_date(string str) { this->release_date = str; } //for setting release date
void Book::set_lang(string str) { this->lang = str; }                           //for setting language of the book