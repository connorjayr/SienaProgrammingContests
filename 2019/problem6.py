# TODO: correct, but needs more optimization

from itertools import permutations

def assign(word: str, assignment: dict) -> int:
  for letter, digit in assignment.items():
    word = word.replace(letter, str(digit))
  return -1 if word.startswith("0") else int(word)

def correct_assignment(words: list, assignment: dict) -> bool:
  assigned = [assign(word, assignment) for word in words]
  return -1 not in assigned and sum(assigned[:-1]) == assigned[-1]

words = input().split()
letters = list(set("".join(words)))
assignments = []
for assignment in permutations(range(10), len(letters)):
  assignment = dict(zip(letters, assignment))
  if correct_assignment(words, assignment):
    assignments.append(assignment)

if len(assignments) == 0:
  print("NO SOLUTION")
  exit(0)

assignments.sort(key=lambda assignment: [assign(word, assignment) for word in words])
print(" ".join([str(n) for n in [assign(word, assignments[0]) for word in words]]))