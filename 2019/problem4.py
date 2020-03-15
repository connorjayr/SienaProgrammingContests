def get_directed_word(puzzle: list, r: int, c: int, d: tuple, max_len: int) -> str:
  word = ""
  while len(word) < max_len and 0 <= r < len(puzzle) and 0 <= c < len(puzzle[r]):
    word += puzzle[r][c]
    r += d[0]
    c += d[1]
  return word

directions = ((1, 0), (1, -1), (0, -1), (-1, -1), (-1, 0), (-1, 1), (0, 1), (1, 1))

rows, cols = [int(n) for n in input().split()]
puzzle = [input() for r in range(rows)]
words_len = int(input())
words = [input() for i in range(words_len)]

max_word_len = max([len(word) for word in words])

found = set()
for r in range(rows):
  for c in range(cols):
    for d in directions:
      curr_word = get_directed_word(puzzle, r, c, d, max_word_len)
      for word in words:
        if curr_word.startswith(word):
          found.add(word)

for word in words:
  print(word, "FOUND" if word in found else "NOT FOUND")