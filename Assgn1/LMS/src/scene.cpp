#include "../include/scene.h"
Scene::Scene() { this->Dialogues = vector<Dialogue>(); } //CR
Scene::Scene(const Scene &other)                         //CCR
{
    this->Dialogues = other.Dialogues;
}
vector<Dialogue> Scene::get_all() { return Dialogues; } //returns all dialogues
int Scene::len() { return Dialogues.size(); }           // ret no. of dialogues
void Scene::add(Dialogue D) { Dialogues.push_back(D); } //for adding a dialogue
Scene& Scene::operator=(const Scene &other)              //CAO
{
    this->Dialogues = other.Dialogues;
    return *this;
}
Dialogue Scene::get(int ind) { return this->Dialogues[ind]; } //for getting dialogue at ind