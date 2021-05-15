from setuptools import setup, find_packages

setup(
    name = 'My_package',
    version = '1.0',
    author = 'Anurat_19CS10071',
    author_email = 'anuratb@yahoo.com',
    packages = find_packages(),
    license='Creative Commons Attribution-Noncommercial-Share Alike license',    
    install_requires = [
        'numpy',
        'matplotlib',
        'Pillow',
        'torch',
        'torchvision'
    ]
)
