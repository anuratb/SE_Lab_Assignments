#Imports
from math import floor

from PIL import  Image

class RescaleImage(object):
    '''
        Rescales the image to a given size.
    '''

    def __init__(self, output_size):
        '''
            Arguments:
            output_size (tuple or int): Desired output size. If tuple, output is
            matched to output_size. If int, smaller of image edges is matched
            to output_size keeping aspect ratio the same.
        '''

        # Write your code here
        self.output_sz = output_size

    def __call__(self, image):
        '''
            Arguments:
            image (numpy array or PIL image)

            Returns:
            image (numpy array or PIL image)

            Note: You do not need to resize the bounding boxes. ONLY RESIZE THE IMAGE.
        '''
        # Write your code here
        img = image.copy()
        if(isinstance(self.output_sz,int)):
            h,w = img.size
            if(h<w):
                w = self.output_sz*w/h
                h = self.output_sz
            else:
                h = self.output_sz*h/w
                w = self.output_sz
            return img.resize((floor(h),floor(w)))
        elif(isinstance(self.output_sz,tuple)):
            try:
                return img.resize(self.output_sz)
            except Exception as e:
                print(e)