from setuptools import setup, find_packages

setup(
    name = 'My_package',
    version = '1.0',
    author = 'Anurat_19CS10071',
    author_email = 'anuratb@yahoo.com',
    packages = find_packages(),
    license='Creative Commons Attribution-Noncommercial-Share Alike license',
    long_description=open('README.md').read(),
    install_requires = [
        'numpy',
        'matplotlib',
        'Pillow',
        'torch',
        'torchvision'
    ]
)
