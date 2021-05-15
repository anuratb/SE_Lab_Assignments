#include "../include/play.h"
Play::Play() {}                                                                                                                                                                                           //CR
Play::Play(string _title, string _author, string _release_date, string lang, string file_name, string P) : Book(_title, _author, _release_date, lang, file_name) { Prologue = P; }                        //CR
Play::Play(string _title, string _author, string _release_date, string lang, string file_name, string P, vector<Act> As, vector<Character> Chars) : Book(_title, _author, _release_date, lang, file_name) //CR
{
    Prologue = P;
    Acts = As;
    Characters = Chars;
}
Play::Play(const Play &other) : Book(other) //CCR
{
    Prologue = other.Prologue;
    Acts = other.Acts;
    Characters = other.Characters;
}
Play &Play::operator=(const Play &other) //CAO
{
    this->Book::operator=(other);
    Prologue = other.Prologue;
    Acts = other.Acts;
    Characters = other.Characters;
    return *this;
}
void Play::add_act(Act A) { this->Acts.push_back(A); }              //to add an act
void Play::add_char(Character C) { this->Characters.push_back(C); } //to add Character C
string Play::get_type()                                             //returns type
{
    string str = "Play";
    return str;
}
vector<Act> Play::get_all_act() { return this->Acts; }              //to get vector of all acts
vector<Character> Play::get_all_char() { return this->Characters; } //to get vectpor all characters
int Play::act_len() { return this->Acts.size(); }                   //to get no. of acts
int Play::char_len() { return this->Characters.size(); }            //to get no. of characters
Act Play::get_act(int ind) { return Acts[ind]; }                    //to get act at index ind
Character Play::get_char(int ind) { return this->Characters[ind]; } //To get charecter at index i
void Play::set_prologue(string str) { Prologue = str; }             //to set Prologue
void Play::print_asso_char(string nme)                              //To print associated chracters
{
    int flag = 0;
    for (Character itr : get_all_char())
    {
        //cout << itr.get_name() << endl; //DEBUG
        if (strcasecmp(itr.get_name().c_str(), nme.c_str()) == 0)
        {
            flag = 1;
            for (string ittr : itr.co_name())
            {
                cout << "    " << ittr << endl;
            }
            break;
        }
    }
    if (!flag)
    {
        cout << "    Sorry! No character with name, " << nme << endl;
    }
    else
    {
        cout << "    are associated with Character, " << nme << endl;
    }
}
