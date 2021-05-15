#19CS10071
#Anurat Bhattacharya
class Group:
    def __init__(self,id):
        self.__MemList = []
        self.__MessList = []
        self.__id = id
    def addMem(self,mem):
        self.__MemList.append(mem)
    def addMess(self,mess):
        self.__MessList.append(mess)
    def getId(self):
        return self.__id
    def getMem(self):
        return self.__MemList