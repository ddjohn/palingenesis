import { Session } from 'meteor/session';
import { Template     } from 'meteor/templating';

import './ChartView.html';
import { Items } from './ItemsApi.js';

//-----------------------------------

Template.chart.onCreated(function bodyOnCreated() {
	console.log("chart::onCreated()");
	Session.set('serie', 'waterplant');
	Meteor.subscribe('items');
});

function buildChart() {
	console.log('buildChart()');

	new Chartist.Line('.ct-chart-day', {
		//labels: Session.get('labels', 'dry', 'moist'),
		//labels: Session.get('itemsday'),
		series: [ 
			Session.get('itemsday'),
		],
	},{
		axisX: {
			showGrid: false,
		},
		axisY: {
			onlyInteger: true,
		},
		low: 0,
		showArea: true,
		showLine: true,
		showPoint: false,
		lineSmooth: true,
	});

	new Chartist.Line('.ct-chart', {
		//labels: Session.get('labels', 'dry', 'moist'),
		series: [ 
			Session.get('items'), Session.get('high'), Session.get('low')
		],
	},{
		axisX: {
			showGrid: false,
		},
		axisY: {
			onlyInteger: true,
		},
		low: 0,
		showArea: true,
		showLine: true,
		showPoint: false,
		lineSmooth: true,
	});
};

Template.chart.helpers({
	items() {
		Session.set('low', Items.find({'name':Session.get('serie')}).fetch().map(x => 30));
		Session.set('high', Items.find({'name':Session.get('serie')}).fetch().map(x => 70));
		Session.set('items', Items.find({'name':Session.get('serie')}).fetch().map(x => x.value));
		Session.set('itemsday', Items.find({'name':Session.get('serie')}).fetch().slice(-144,-1).map(x => x.value));
		//Session.set('labels', Items.find().fetch({name:Session.get('serie')}).map(x => x._id));
		buildChart();
		return Items.find({'name':Session.get('serie')});
	},
	serie() {
		return Session.get('serie');
	},
});

Template.chart.onRendered(function() {
	Tracker.autorun(function () {
		buildChart();
	});
});
