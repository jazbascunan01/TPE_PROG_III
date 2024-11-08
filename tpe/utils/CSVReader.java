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
	
	public void readTareas(String tareaPath, HashMap<String, Tarea> tareaHashMap, LinkedList<Tarea> criticas, LinkedList<Tarea> noCriticas, Tree priorityTareaTree) {
		ArrayList<String[]> lines = this.readContent(tareaPath);
		
		// Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
		for (String[] line: lines) {
			String id = line[0].trim();
			String nombre = line[1].trim();
			Integer tiempo = Integer.parseInt(line[2].trim());
			Boolean critica = Boolean.parseBoolean(line[3].trim());
			Integer prioridad = Integer.parseInt(line[4].trim());
			
			Tarea tarea = new Tarea(id, nombre, tiempo, critica, prioridad);
			
			addTareaHash(tarea, tareaHashMap);
			addTareasCriticals(tarea, criticas, noCriticas);
			addTareaTree(tarea, priorityTareaTree);
		}
		
	}
	
	private void addTareaHash(Tarea tarea, HashMap<String, Tarea> tareaHashMap){
		String id = tarea.getId();
		tareaHashMap.put(id, tarea);
	}
	
	private void addTareasCriticals(Tarea tarea, LinkedList<Tarea> criticas, LinkedList<Tarea> noCriticas){
		if(tarea.getCritica())
			criticas.addFirst(tarea);
		else 
			noCriticas.addFirst(tarea);
	}

	private void addTareaTree(Tarea tarea, Tree tree){
		tree.add(tarea.getPrioridad(), tarea);
	}
	
	public void readProcesadores(String procesadorPath, ArrayList<Procesador> procesadores) {
		
		ArrayList<String[]> lines = this.readContent(procesadorPath);
		
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
