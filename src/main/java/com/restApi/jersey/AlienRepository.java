package com.restApi.jersey;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlienRepository {
	Connection con;

	public AlienRepository() {
		String url = "jdbc:sqlserver://localhost:1433;databaseName=restdb;";
		String userName = "root";
		String password = "12341234";
		try {
			
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			con = DriverManager.getConnection(url, userName, password);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public List<Alien> getAliens() {
		List<Alien> aliens = new ArrayList<Alien>();
		String sql = "SELECT * FROM alien";
		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while (rs.next()) {
				Alien a = new Alien();
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));

				aliens.add(a);

			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return aliens;
	}

	public Alien getAlien(int id) {
		String sql = "SELECT * FROM alien where id = " + id;
		Alien a = new Alien();

		try {
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery(sql);
			if (rs.next()) {
				a.setId(rs.getInt(1));
				a.setName(rs.getString(2));
				a.setPoints(rs.getInt(3));

			}

		} catch (Exception e) {
			System.out.println(e);
		}
		return a;

	}

	public void create(Alien alien) {
		String sql = "insert into alien values(?,?,?) ";
		try {

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, alien.getId());
			st.setString(2, alien.getName());
			st.setInt(3, alien.getPoints());
			st.executeQuery();
			st.close();

		}

		catch (Exception e) {
			System.out.println(e);
		}

	}

}
