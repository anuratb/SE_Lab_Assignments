# Imports
import random
from math import floor, ceil

from PIL import  Image


class CropImage(object):
    '''
        Performs either random cropping or center cropping.
    '''

    def __init__(self, shape, crop_type='center'):
        '''
            Arguments:
            shape: output shape of the crop (h, w)
            crop_type: center crop or random crop. Default: center
        '''
        self.shape = shape
        self.crop_type = crop_type

        # Write your code here

    def __call__(self, image):
        '''
            Arguments:
            image (numpy array or PIL image)

            Returns:
            image (numpy array or PIL image)
        '''
        # Using PIL image
        img=image.copy()
        h, w = img.size
        if self.crop_type == 'center':
            try:
                img =  img.crop((floor((w - self.shape[1]) / 2), floor((h - self.shape[0]) / 2),ceil((w + self.shape[1]) / 2), ceil((h + self.shape[0]) / 2)))
                return img
            except Exception as e:
                print(e)
        elif self.crop_type == 'random':
            top_left = (random.randint(0,h-self.shape[0]),random.randint(0,w-self.shape[1]))
            try:
                img = img.crop((top_left[1], top_left[0],top_left[1]+self.shape[1], top_left[0]+self.shape[0]))
                return img
            except Exception as e:
                print(e)

        # Write your code here
