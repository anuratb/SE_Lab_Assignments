#include "../include/character.h"
Character::Character() {}                       //CR
Character::Character(string nme) : name(nme) {} //CR
Character::Character(const Character &other)    //CCR
{
    this->name = other.name;
    this->associated_char = other.associated_char;
}
Character &Character::operator=(const Character &other) //CAO
{
    this->name = other.name;
    this->associated_char = other.associated_char;
    return *this;
}
string Character::get_name() { return this->name; }                                 //for getting name
void Character::set_name(string nme) { this->name = nme; }                          //for setting name
void Character::add_co(string char_nme) { this->associated_char.insert(char_nme); } //for adding co character
int Character::len() { return (associated_char.size()); }                           //for getting total of number of co actors
vector<string> Character::co_name()                                                 //for getting names of co acters
{
    vector<string> char_nme;
    for (string itr : associated_char)
    {
        char_nme.push_back(itr);
    }
    return char_nme;
}