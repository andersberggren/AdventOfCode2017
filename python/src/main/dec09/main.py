import re

from aoclib.filereader import get_file_as_string

class Evaluator:
	def __init__(self, character_stream):
		self.character_stream = character_stream
		self.non_cancelled_garbage = 0
		self.score = self._get_score()
	
	def _get_score(self, depth=1):
		self.character_stream = self.character_stream[1:]
		score = depth
		while True:
			symbol = self.character_stream[0]
			if symbol == "{":
				score += self._get_score(depth+1)
			elif symbol == "}":
				self.character_stream = self.character_stream[1:]
				return score
			elif symbol == "<":
				garbage = re.match("^(<(!.|[^!>])*>)", self.character_stream).group(1)
				self.character_stream = self.character_stream[len(garbage):]
				self.non_cancelled_garbage += len(re.sub("!.", "", garbage[1:-1]))
			elif symbol == ",":
				self.character_stream = self.character_stream[1:]
			else:
				raise ValueError("Unexpected symbol: {}".format(symbol))

character_stream = get_file_as_string("input09.txt").strip()
e = Evaluator(character_stream)
print("Total score:", e.score)
print("Number of non-cancelled garbage characters:", e.non_cancelled_garbage)
