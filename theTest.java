package IDMA_Libraries_Alpha;

import IDMA_Libraries_Alpha.utils.Handler;

public class theTest extends Game {

		private static final long serialVersionUID = 1L;
		
		@SuppressWarnings("unused")
		private Handler handler;
		private Core core;
		
		public theTest(int w, int h) {
			super(w, h, "JustATest");
			
			this.handler = new Handler(core);

		}
		
		public static void main(String[] args) {
			new theTest(600, 400);
		}

	}
