#include "../include/novel.h"
Novel::Novel(){};                                                                                                                                                                                           //CR
Novel::Novel(string _title, string _author, string _release_date, string lang, string file_name) : Book(_title, _author, _release_date, lang, file_name) {}                                                 //CR
Novel::Novel(string _title, string _author, string _release_date, string lang, string file_name, vector<Chapter> chaps) : Book(_title, _author, _release_date, lang, file_name) { this->chapters = chaps; } //CR
Novel::Novel(const Novel &other) : Book(other)                                                                                                                                                              //CCR
{
    this->chapters = other.chapters;
}
Novel &Novel::operator=(const Novel &other) //CAO
{
    this->Book::operator=(other);
    this->chapters = other.chapters;
    return *this;
}
string Novel::get_type() //to get type
{
    string str = "Novel";
    return str;
}
int Novel::len() { return this->chapters.size(); } //for getting no. of chapters
void Novel::add_chap(Chapter cp)                   //for adding a chapter
{
    this->chapters.push_back(cp);
    chapters.back().set_chap_no(chapters.size());    
}
Chapter Novel::get(int ind) { return this->chapters[ind]; }      //for getting chapter at index ind
vector<Chapter> Novel::get_all_chap() { return this->chapters; } //for getting all chapters in a vector