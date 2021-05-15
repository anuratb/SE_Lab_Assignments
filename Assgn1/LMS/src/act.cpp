#include "../include/act.h"
Act::Act() {}              //CC
Act::Act(const Act &other) //CCR
{
    this->Scenes = other.Scenes;
}
void Act::add_sc(Scene S) { this->Scenes.push_back(S); } //for adding scene
vector<Scene> Act::getall() { return Scenes; }           //for returning all Scenes
Act& Act::operator=(const Act &other)                     //CAO
{
    this->Scenes = other.Scenes;
    return *this;
}
int Act::len() { return Scenes.size(); } //for getting number of scenes
