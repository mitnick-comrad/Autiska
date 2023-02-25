import nltk
nltk.download('punkt')
from nltk.stem.lancaster import LancasterStemmer
stemmer= LancasterStemmer()
from keras.datasets import imdb
import keras
import numpy
import tflearn
import tensorflow as tf
import random
import json
import os


with open("intents.json") as file :
  data= json.load(file)
words =[]
lables = []
docs= []
docs_x=[]
docs_y=[]
def body():
  for intent in data["intents"]:
    for pattern in intent["patterns"]:
      wrds = nltk.word_tokenize(pattern)
      words.extend(wrds)
      docs_x.append(wrds)
      docs_y.append(intent["tag"])

      if intent["tag"] not in lables:
        lables.append(intent["tag"])
  words = [stemmer.stem(w.lower()) for w in words if w not in "?"]
  words= sorted(list(set(words)))

  lables = sorted(lables)

  training =[]
  output = []


  out_empty= [0 for _ in range(len(lables))]



  for x, doc  in enumerate(docs_x):
    bag = []

    wrds=[stemmer.stem(w) for w in doc]

    for w in words:
      if w in wrds:
        bag.append(1)
      else:
        bag.append(0)
    output_row = out_empty[:]
    output_row[lables.index(docs_y[x])] =1

    training.append(bag)
    output.append(output_row)

  training = numpy.array(training)
  output = numpy.array(output)

  tf.reset_default_graph()
  net = tflearn.input_data(shape= [None, len(training[0])])
  net = tflearn.fully_connected(net,8)
  net = tflearn.fully_connected(net,8)
  net = tflearn.fully_connected(net,len(output[0]), activation="softmax")
  net = tflearn.regression(net)


  model = tflearn.DNN(net)

  model.fit(training, output, n_epoch=1000, batch_size=8, show_metric=True)
  model.save("model.tflearn")
  checkpoint_dir = './training_checkpoints'
  # Name of the checkpoint files
  checkpoint_prefix = os.path.join(checkpoint_dir, "ckpt_{epoch}")

  checkpoint_callback=tf.keras.callbacks.ModelCheckpoint(
      filepath=checkpoint_prefix,
      save_weights_only=True)




def bag_of_words(s, words):
  bag = [0 for _ in range(len(words))]
  s_words = nltk.word_tokenize(s)
  s_words = [stemmer.stem(word.lower()) for word in s_words]

  for se in s_words:
    for i, w in enumerate(words):
      if w == se:
        bag[i]=1
  return numpy.array(bag)

def chat(al):
  body()
  #a= false
  #if a== false:
   # a=true
    #return("Lets talk")
  while True:
    #inp = input("You: ")
    inp=al;
    if inp.lower()=="quit":
      break
    results = model.predict([bag_of_words(inp, words)])
    results_index = numpy.argmax(results)
    tag = lables[results_index]
    for tg in data["intents"]:
      if tg['tag']== tag:
        responses = tg['responses']
    return(str(random.choice(responses)))
#chat()
