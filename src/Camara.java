import javax.swing.*;
import java.awt.*;

public class Camara{
	public vec3 position = new vec3(0,0,-600);
	public vec2 angle = new vec2(1,3.6);
	public Ray[] ray = new Ray[Main.canvas.pixel.length];
	public Controler controls = new Controler();

	public Color castRays(Pixel pixel){
		int id = (int) (pixel.id.x+pixel.id.y*Main.canvas.pWidth);

		this.ray[id] = new Ray(
			this.position,
			new vec3(
				(pixel.id.x-Main.canvas.pWidth/2)*Math.cos(this.angle.y),
				(pixel.id.y-Main.canvas.pHeight/2)*Math.sin(this.angle.x),
				Math.sin(this.angle.y)
			)
		);

		Sphere bola = new Sphere(new vec3(0,0,-10),5); //-10, 10

		return bola.colide(this.ray[id])?new Color(0xffffff):new Color(0x000000);
	}

	public void move(){
		if(this.controls.wDown){
			this.position.x += Math.sin(this.angle.y);
			this.position.z += Math.cos(this.angle.y);
		} 
		if(this.controls.sDown){
			this.position.x -= Math.sin(this.angle.y);
			this.position.z -= Math.cos(this.angle.y);
		}
		if(this.controls.dDown){
			this.position.x += Math.sin(this.angle.y+Math.PI/2);
			this.position.z += Math.cos(this.angle.y+Math.PI/2);
		} 
		if(this.controls.aDown){
			this.position.x -= Math.sin(this.angle.y+Math.PI/2);
			this.position.z -= Math.cos(this.angle.y+Math.PI/2);
		}
		this.position.y += this.controls.eDown?1:0;
		this.position.y -= this.controls.qDown?1:0;

		this.angle.x += this.controls.uArrow?0.1:0;
		this.angle.x -= this.controls.dArrow?0.1:0;
		this.angle.y += this.controls.rArrow?0.1:0;
		this.angle.y -= this.controls.lArrow?0.1:0;
	}
}