bayesNet = {'Burglary' : [[], .001], 
			'Earthquake' : [[], .002], 
			'Alarm' : [['Burglary', 'Earthquake'], .95, .94, .29, .001], 
			'JohnCalls' : [['Alarm'], .9, .05],
			'MaryCalls' : [['Alarm'], .7, .01]
		   }

bnVars = ['Burglary', 'Earthquake', 'Alarm', 'JohnCalls', 'MaryCalls']

e = {'MaryCalls' : True, 'JohnCalls': False}
X = {'Earthquake' : False}


def enumerationAsk(X, e):

	numeratorHiddenVariables = getHiddenVariables(X, e)
	numeratorSum = 0.0
	hiddenVariableAssignments = booleanAssignments(len(numeratorHiddenVariables))
	currentAssignment = X.copy()
	currentAssignment.update(e)

	for assignment in hiddenVariableAssignments:
		
		for i in range(0, len(numeratorHiddenVariables)):
			currentAssignment[numeratorHiddenVariables[i]] = assignment[i]

		numeratorSum += goDownNetwork(currentAssignment)

		for i in range(0, len(numeratorHiddenVariables)):
			del currentAssignment[numeratorHiddenVariables[i]]
			

	denominatorHiddenVariables = getHiddenVariables(None, e)
	denominatorSum = 0.0
	hiddenVariableAssignments = booleanAssignments(len(denominatorHiddenVariables))
	currentAssignment = e.copy()

	for assignment in hiddenVariableAssignments:
		
		for i in range(0, len(denominatorHiddenVariables)):
			currentAssignment[denominatorHiddenVariables[i]] = assignment[i]

		denominatorSum += goDownNetwork(currentAssignment)

		for i in range(0, len(denominatorHiddenVariables)):
			del currentAssignment[denominatorHiddenVariables[i]]


 	print numeratorSum/denominatorSum


def booleanAssignments(length):

	import itertools

	if length <= 0:
		return []

	assignments = []

	for x in range(0, length+1):

		currentAssignment = []

		for i in range(1, x+1):
			currentAssignment.append(True)

		for j in range(1, length - x + 1):
			currentAssignment.append(False)

		assignments += list(set(list(itertools.permutations(currentAssignment, length))))

	return assignments



def goDownNetwork(variableAssignment):
	
	burglaryValue = variableAssignment['Burglary']
	burglaryProb = bayesNet['Burglary'][1]

	if burglaryValue == False:
		burglaryProb = 1 - burglaryProb

	earthquakeValue = variableAssignment['Earthquake']
	earthquakeProb = bayesNet['Earthquake'][1]

	if earthquakeValue == False:
		earthquakeProb = 1 - earthquakeProb	

	alarmValue = variableAssignment['Alarm']
	alarmProb = bayesNet['Alarm'][1]

	if burglaryValue == True and earthquakeValue == False:
		alarmProb = bayesNet['Alarm'][2]
	
	if burglaryValue == False and earthquakeValue == True:
		alarmProb = bayesNet['Alarm'][3]

	if burglaryValue == False and earthquakeValue == False:
		alarmProb = bayesNet['Alarm'][4]

	if alarmValue == False:
		alarmProb = 1 - alarmProb	

	johnCallsValue = variableAssignment['JohnCalls']
	johnCallsProb = bayesNet['JohnCalls'][1]

	if alarmValue == False:
		johnCallsProb = bayesNet['JohnCalls'][2]

	if johnCallsValue == False:
		johnCallsProb = 1 - johnCallsProb	

	maryCallsValue = variableAssignment['MaryCalls']
	maryCallsProb = bayesNet['MaryCalls'][1]

	if alarmValue == False:
		maryCallsProb = bayesNet['MaryCalls'][2]

	if maryCallsValue == False:
		maryCallsProb = 1 - maryCallsProb

	return burglaryProb*earthquakeProb*alarmProb*johnCallsProb*maryCallsProb	



def getHiddenVariables(X, e):

	nonHiddenVariables = []

	if X != None:
		nonHiddenVariables = X.keys() + e.keys()
	else:
		nonHiddenVariables = e.keys()

	hiddenVariables = []

	for var in bnVars:
		if var not in nonHiddenVariables:
			hiddenVariables.append(var)	

	return hiddenVariables



enumerationAsk(X, e)


# def enumerationAsk(X, e):

# 	Q = {}

# 	for xi in X:

# 		e[xi] = X[xi]
# 		Q[xi] = enumerateAll(bnVars, e)
# 		del e[xi]

# 	return normalize(Q)

# def enumerateAll(varsList, evidence):

# 	if varsList is None:
# 		return 1.0

# 	Y = varsList[0]

# 	if Y in evidence:
    	
#     	pyGivenParentsY = getPyGivenParentsY()

# 	return


# def getPyGivenParentsY():

	

# def normalize(Q):
#  	return 

# enumerationAsk(X, e)
	

