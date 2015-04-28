package discovery;



import itat.zttc.login.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class DiscoveryFragment extends Fragment
{
	@SuppressWarnings("unused")
	private SimpleAdapter simpleAdapter;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState)
	{
		 final   View view=inflater.inflate(R.layout.discovery, container, false);
       return view;
	}
}
