# In this python file, only the definations for the magic functions and the basic operations
# for the question segments are provided. There may be the need to add new functions or overload
# existing ones as per the question requirements.

class Vector:

    def __init__(self, *args):
        # if arg is an int(dimension)
        if isinstance(args[0], int):
            self._coords = [0] * args[0]
        elif hasattr(args[0],'__iter__'):
            self._coords = [x for x in args[0]]

    def __len__(self):
        return len(self._coords)

    # return the dimension of the vector

    def __getitem__(self, j):
        return self._coords[j]

    # return the jth coordinate of the vector

    def __setitem__(self, j, val):
        self._coords[j] = val

    # set the jth coordinate of vector to val

    def __add__(self, other):
        try:
            if(len(self)!=len(other)):
                raise Exception('Dimension Mismatch')
            return Vector([(self[i] + other[i]) for i in range(len(self))])
        except Exception as e:
            print(e)

    # u + v

    def __eq__(self, other):
        if (len(self) != len(other)):
            return False
        return not False in [(self[i] == other[i]) for i in range(len(self))]
    # return True if vector has same coordinates as other

    def __ne__(self, other):
        if (len(self) != len(other)):
            return True
        return False in [(self[i] == other[i]) for i in range(len(self))]

    # return True if vector differs from other

    def __str__(self):
        s = '< '
        for elem in self._coords:
            s = s + str(elem) + ', '
        s = s.strip()
        s = s.strip(',')
        s+=' >'
        return s

    # return the string representation of a vector within <>

    def __sub__(self, other):
        try:
            if(len(self)!=len(other)):
                raise Exception('Dimension Mismatch')
            return Vector([(self[i] - other[i]) for i in range(len(self))])
        except Exception as e:
            print(e)
    # Soln for Qs. 2

    def __neg__(self):
        return Vector([(-self[i]) for i in range(len(self))])
    # Soln for Qs. 3

    def __rmul__(self, value):
        return (self * value)

    def __mul__(self, other):
        if isinstance(other,Vector):  #Dot Product
            try:
                if(len(self)!=len(other)):
                    raise  Exception('Dimension Mismatch')
                return sum([self[i]*other[i] for i in range(len(self))])
            except Exception as e:
                print(e)
        else:  #Broadcast
            return Vector([(self[i] * other) for i in range(len(self))])


# Soln for Qs. 4, 5 and 6

def main():
    v1 = Vector(5)
    v2 = Vector(7)
    v3 = Vector([1, 2, 3, 4, 5])

    # Add suitable print statements to display the results
    # of the different question segments

    print('Constructing from size: v1: '+str(v1))
    print('Constructing from size: v2: '+str(v2))
    print('Constructing from list: v3: '+str(v3))
    #Assign value to v1 and v2
    for i in range(5): v1[i] = i
    for i in range(7): v2[i] = i
    print('After Assigning values to v1 and v2 ')
    print('v1: ' + str(v1))
    print('v2: ' + str(v2))
    print()
    #Testing len
    print('Length of v1: ' + str(len(v1)))
    print('Length of v2: ' + str(len(v2)))
    print('Length of v3: ' + str(len(v3)))
    print()
    #Tessting get item
    print('v1[2]: '+str(v1[2]))
    print()
    #Testing Addition
    print('v1: ' + str(v1))
    print('v3: ' + str(v3))
    print('v1 + v3 = '+str(v1+v3))
    #Testing Equality
    print('v1 == v3: '+str(v1==v3))
    v4 = Vector([0,1,2,3,4])
    print('v4 = '+ str(v4))
    print('v1 == v4: ' + str(v1 == v4))
    print()
    #Testing Non Equality
    print('v1 != v3: ' + str(v1 != v3))
    print('v1 != v4: ' + str(v1 != v4))
    print()
    #Testing Subtraction
    v5 = Vector([5,8,1,3,-1])
    print('v5: ' + str(v5))
    print('v3: ' + str(v3))
    print('v5 - v3 = ' + str(v5 - v3))
    print()
    #Testing Negation
    print('v3 = '+str(v3) )
    print('-v3 = '+str(-v3))
    print()
    #Testing Multiplication and reverse multiplication
    print('v2: '+str(v2))
    print('Broadcasting: ')
    print('v2 * 3 = '+str(v2*3))
    print('3 * v2 = '+str(3*v2))
    print()
    print('v4: ' + str(v4))
    print('v3: ' + str(v3))
    print('Dot Product: ')
    print('v4 * v3 = ' + str(v4 * v3))
    print('v3 * v4 = ' + str(v3 * v4))






if __name__ == '__main__':
    main()