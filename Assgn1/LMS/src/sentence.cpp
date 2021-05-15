#include "../include/sentence.h"
Sentence::Sentence() { this->Words = vector<string>(); } //CR
Sentence::Sentence(const Sentence &other)                //CCR
{
    this->Words = other.Words;
}
Sentence &Sentence::operator=(const Sentence &other) //CAO
{
    this->Words = other.Words;
    return *this;
}
void Sentence::add_word(string str) //for adding word
{
    if (str.size() == 0)
        return;
    this->Words.push_back(str);
}
vector<string> Sentence::get_all() { return this->Words; } //for getting all words
int Sentence::len() { return this->Words.size(); }         //for getting no. of words
string Sentence::get(int ind) { return this->Words[ind]; } //for getting word at index ind(0 indexed)