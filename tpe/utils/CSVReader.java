package tpe.utils;

import tpe.Procesador;
import tpe.Tarea;
import tpe.Tree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class CSVReader {

	public CSVReader() {
	}
	
	public void readTareas(String taskPath, HashMap<String, Tarea> taskHashMap, LinkedList<Tarea> criticas, LinkedList<Tarea> noCriticas, Tree priorityTareaTree) {
		ArrayList<String[]> lines = this.readContent(taskPath);
		
		// Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
		for (String[] line: lines) {
			String id = line[0].trim();
			String nombre = line[1].trim();
			Integer tiempo = Integer.parseInt(line[2].trim());
			Boolean critica = Boolean.parseBoolean(line[3].trim());
			Integer prioridad = Integer.parseInt(line[4].trim());
			
			Tarea task = new Tarea(id, nombre, tiempo, critica, prioridad);
			
			addTareaHash(task, taskHashMap);
			addTareasCriticals(task, criticas, noCriticas);
			addTareaTree(task, priorityTareaTree);
		}
		
	}
	
	private void addTareaHash(Tarea task, HashMap<String, Tarea> taskHashMap){
		String id = task.getId();
		taskHashMap.put(id, task);
	}
	
	private void addTareasCriticals(Tarea task, LinkedList<Tarea> criticas, LinkedList<Tarea> noCriticas){
		if(task.getCritica())
			criticas.addFirst(task);
		else 
			noCriticas.addFirst(task);
	}

	private void addTareaTree(Tarea task, Tree tree){
		tree.add(task.getPrioridad(), task);
	}
	
	public void readProcesadores(String processorPath, ArrayList<Procesador> procesadores) {
		
		ArrayList<String[]> lines = this.readContent(processorPath);
		
		for (String[] line: lines) {
			String id = line[0].trim();
			String codigo = line[1].trim();
			Boolean refrigerado = Boolean.parseBoolean(line[2].trim());
			Integer anio = Integer.parseInt(line[3].trim());
			Procesador p = new Procesador(refrigerado, id, codigo, anio);
			if(!procesadores.contains(p)){
				procesadores.add(p);
			}

		}
		
	}

	private ArrayList<String[]> readContent(String path) {
		ArrayList<String[]> lines = new ArrayList<String[]>();

		File file = new File(path);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				lines.add(line.split(";"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}
		
		return lines;
	}
	
}
