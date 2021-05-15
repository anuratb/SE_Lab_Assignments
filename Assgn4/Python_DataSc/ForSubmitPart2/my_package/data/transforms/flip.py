#Imports
from PIL import  Image

class FlipImage(object):
    '''
        Flips the image.
    '''

    def __init__(self, flip_type='horizontal'):
        '''
            Arguments:
            flip_type: 'horizontal' or 'vertical' Default: 'horizontal'
        '''
        self.flip_type = flip_type
        # Write your code here

        
    def __call__(self, image):
        '''
            Arguments:
            image (numpy array or PIL image)

            Returns:
            image (numpy array or PIL image)
        '''
        img = image.copy()
        if(self.flip_type=='horizontal'):
            return img.transpose(Image.FLIP_LEFT_RIGHT)
        elif(self.flip_type == 'vertical'):
            return img.transpose(Image.FLIP_TOP_BOTTOM)



        # Write your code here

       