#19CS10071
#Anurat Bhattacharya
class User :
    '''
    __id : Unique user id
    __ContList : List of Contacts
    '''
    def __init__(self,id):
        self.__id = id
        self.__ContList = []
        self.__RecvMess = []
        self.__GrpList = []
        self.__SentMess = []
    def addCont(self,cont):
        self.__ContList.append((cont))
    def addGrp(self,grp):
        self.__GrpList.append(grp)
    def addRecvMess(self,mess):
        self.__RecvMess.append(mess)
    def addSent(self,mess):
        self.__SentMess.append(mess)
    def getId(self):
        return self.__id
    def getGrpList(self):
        return self.__GrpList
    def getContList(self):
        return self.__ContList
    def getRecvMess(self):
        return  self.__RecvMess