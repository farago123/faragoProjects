import random

Alarm = {(True, True) :  .95, 
		 (True, False):  .94,
		 (False, True):  .29,
		 (False, False): .001,
		}

JohnCalls = {True : .9,
			 False : .05
			}

MaryCalls = {True : .7,
			 False : .01
			}


def priorSample():

	x = []

	x.append(sampleNode(.001))
	x.append(sampleNode(.002))

	alarmTrue = Alarm[(x[0], x[1])]

	x.append(sampleNode(alarmTrue))

	johnCallsTrue = JohnCalls[x[2]]

	x.append(sampleNode(johnCallsTrue))

	maryCallsTrue = MaryCalls[x[2]]

	x.append(sampleNode(maryCallsTrue))

	return x


def sampleNode(probabilityOfTrue):
	
	randomNumber = random.randint(0, 1000000)/1000000.0

	if randomNumber <= probabilityOfTrue:
		return True
	else:
		return False

# i = 0
# count = 0
# numSamples = 1000000.0


# while i < numSamples:
# 	currentSample = priorSample()
# 	if currentSample == [False, False, True, True, True]:
# 		count += 1
# 	i += 1

# print count/numSamples


