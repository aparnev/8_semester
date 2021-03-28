def ListSum(numberList):
    Sum = 0

    for i in numberList:
        Sum = Sum + i
    return Sum

print(ListSum([1, 2, 3, 4, 5, 6, 7, 8]))
