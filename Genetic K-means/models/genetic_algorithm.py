from population import Population
from entity import Entity
import numpy as np


class GeneticAlgorithm:

	def __init__(self, n_clusters: int, population_size: int,
	             mutation_rate: float, X: np.ndarray,
	             crossover_rate: float, elitism_count: int):
		self.population_size = population_size
		self.mutation_rate = mutation_rate
		self.elitism_count = elitism_count
		self.crossover_rate = crossover_rate
		self.n_clusters = n_clusters
		self.data = X

	def init_population(self, population_size: int):
		return Population(self.n_clusters, population_size, self.data)

	def evaluate_fitness(self, population: Population):
		pop_fitness = 0
		for entity in population.get_entities():
			pop_fitness += entity.calc_fitness()
		pop_fitness = 1/pop_fitness
		population.set_fitness(pop_fitness)

	def select_parent(self, population: Population):
		idx = np.random.randint(0, population.get_size())
		idx2 = np.random.randint(0, population.get_size())

		while idx < idx2:
			idx = np.random.randint(0, population.get_size())
			idx2 = np.random.randint(0, population.get_size())

		return population.get_entity(idx)

	def single_point_cross_over(self, parent1: Entity, parent2: Entity):
		child = Entity(self.n_clusters, self.data)

		idx = np.random.randint(0, child.chromosome_length()-1)

		for i in range(child.chromosome_length()):
			if (i < idx):
				child.set_gene(i, parent1.get_gene(i))
			else:
				child.set_gene(i, parent2.get_gene(i))
		return child

	def mutate(self, entity: Entity):
		delta = np.random.rand()
		chance = np.random.randint(0, 2)

		for v in entity.chromosome:
			if v != 0:
				if chance == 0:
					v += 2 * delta * v
				if chance == 1:
					v -= 2 * delta * v
			else:
				if chance == 0:
					v += 2 * delta
				if chance == 1:
					v -= 2 * delta

	def natural_selection(self, population: Population):
		new_population = Population(self.n_clusters, self.population_size, self.data)
		for i in range(population.get_size()):
			parent1 = population.get_fittest()

			if (self.crossover_rate > np.random.rand() and i > self.elitism_count):
				parent2 = self.select_parent(population)
				child = self.single_point_cross_over(parent1, parent2)
				self.mutate(child)
				new_population.set_entity(i, child)
		else:
			new_population.set_entity(i, parent1)
		return new_population

