#include "../include/dialogue.h"
Dialogue::Dialogue() {}                                                                      //CR
Dialogue::Dialogue(string char_nm, string content) : content(content), char_name(char_nm) {} //CR
Dialogue::Dialogue(const Dialogue &other)                                                    //CCR
{
    this->char_name = other.char_name;
    this->content = other.content;
}
string Dialogue::get_cont() { return this->content; }   //to get content
string Dialogue::get_char() { return this->char_name; } //to get speaking character
Dialogue& Dialogue::operator=(const Dialogue &D)         //CAO
{
    this->char_name = D.char_name;
    this->content = D.content;
    return *this;
}